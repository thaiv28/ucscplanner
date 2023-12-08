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

def courses_to_csv():
    subject_pages = find_subject_pages()
    test_urls = ["https://catalog.ucsc.edu/en/current/general-catalog/courses/artg-art-and-design-games-and-playable-media/"]

    ua = UserAgent()
    headers = {'User-Agent': ua.random}
    session = requests.Session()
    retry = Retry(connect=3, backoff_factor=0.5)
    adapter = HTTPAdapter(max_retries=retry)
    session.mount('http://', adapter)
    session.mount('https://', adapter)

    courses = []
    for url in test_urls:
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
    
    
if __name__ == '__main__':
    courses_to_csv()