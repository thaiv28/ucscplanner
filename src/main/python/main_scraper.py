import requests
from bs4 import BeautifulSoup
import numpy as np
import pandas as pd
from url_scraper import find_subject_pages, find_course_pages
from page_scraper import scrape
from requests.adapters import HTTPAdapter
from urllib3.util.retry import Retry
from fake_useragent import UserAgent
import csv


def courses_to_csv(CLASSPATH):
    subject_pages = find_subject_pages()
    test_urls = ["https://catalog.ucsc.edu/en/current/general-catalog/courses/acen-academic-english/"]

    ua = UserAgent()
    headers = {'User-Agent': ua.random}
    session = requests.Session()
    retry = Retry(connect=3, backoff_factor=0.5)
    adapter = HTTPAdapter(max_retries=retry)
    session.mount('http://', adapter)
    session.mount('https://', adapter)

    courses = []
    for url in subject_pages:
        print("Subject started: " + url)
        course_links = find_course_pages(url)
        

        for course_url in course_links:
        
            r = session.get(course_url, headers=headers)
            soup = BeautifulSoup(r.content, 'html5lib')
            
            main = soup.find(id="main")
            
            courses.append(scrape(main))
            
        print("Subject finished: " + url)
            
    df = pd.DataFrame.from_records([c.to_dict() for c in courses])

    print(df)
    df.to_csv(CLASSPATH)
    

def sql_file(CLASSPATH):
    
    with open('sql.txt', 'w') as f:
        
        with open(CLASSPATH, newline='') as csvfile:
            reader = csv.reader(csvfile)
            codes = []
            
            f.write("INSERT INTO course (code, name, subject, num, credits, prof, gen_ed, repeat, quarters, preqs, preqstr) VALUES\n")
            skip_row = False
            for row in reader:
                skip_row = True
                #skips header
                if (row[1] == 'Code'):
                    continue

                if row[1] not in codes:
                    f.write("(")
                    skip_row = False
                for j in range(len(row)):
                    
                    if j == 0:
                        continue
                    # check if code is not unique
                    if skip_row:
                        print("x" + row[j])
                        break
                    else: 
                        codes.append(row[j])
                        
                    # i is value, j is index
                    i = row[j].strip()
                    i = i.replace("&", "and")
                    i = i.replace("'","")
                    
                    if(i == "N/A"):
                        # check if N/A is for credits category
                        if j == 5:
                            f.write("-1")
                        else:
                            f.write("NULL")
                    elif (i.isnumeric()):
                        f.write(i)
                    else:
                        f.write("'" + i + "'")
                    # check if end of row
                    if(j != len(row) - 1):
                        f.write(", ")
                if not skip_row:
                    f.write("),\n")

            
                
# ,Code,Name,Subject,Number,Credits,Professor,Gen-Ed,Repeatable,Quarters Offered,Prereqs
# private String code;
#     private String name;
#     private String subject
#     private String num;
#     private int credits;
#     private String prof;
#     private List<String> genEd;
#     private boolean repeat;
#     private List<String> quarters;
#     private List<String> prereqs;

if __name__ == '__main__':
    CLASSPATH = "../resources/courselist.csv"
    
    # courses_to_csv(CLASSPATH)
    sql_file(CLASSPATH)