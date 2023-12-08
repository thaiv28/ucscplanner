import requests
from bs4 import BeautifulSoup
import numpy as np
import pandas as pd
from course import Course

def scrape(main):
    
    course_title = main.find('h1')
    
    text = course_title.text.strip()
    first_space = text.find(" ")
    second_space = text[first_space + 1:].find(' ') + first_space + 1
    
    code = text[:second_space].replace(" ","")
    name = text[second_space:]
    subject = text[:first_space]
    number = text[first_space:second_space]
    
    professor, gen_ed, qtr, credits, prereqs_p = "N/A", "N/A", "N/A", "N/A", "N/A"
    reqs = []
    repeat = False
    
    extra_fields = main.find_all('div', class_="extraFields")
    for div in extra_fields:
        if(div.find("h4").text.strip() == "Credits"):
            credits = int(div.find("p").text)
        elif(div.find("h4").text.strip() == "Requirements"):
            prereqs_html = div.find_all("a")
            for prereq in prereqs_html:
                reqs.append(prereq.text)
            prereqs_p = div.text.strip()[12:].strip()
        elif(div.find("h4").text.strip() == "Repeatable for credit"):
            if(div.find('p').text == "Yes"): 
                repeat = True
            else: 
                repeat = False
    
    if main.find("div", class_="instructor") is not None:
        professor_class = main.find('div', class_="instructor")
        professor = professor_class.find("p").text
        
    if main.find("div", class_="genEd") is not None:
        gen_class = main.find('div', class_="genEd")
        gen_ed = gen_class.find('p').text
        
    if main.find("div", class_="quarter") is not None:
        qtr_class = main.find('div', class_="quarter")
        qtr = qtr_class.find('p').text

    if len(reqs) == 0:
            reqs = "N/A"
    
    return Course(code, name, subject, number, credits, professor, 
                  reqs, gen_ed, repeat, qtr, prereqs_p)
    
     #takes course number + title (ex: ABC123 Learning the Alphabet) and splits into
    #different two different lists, one of the numbers and one of the titles
        
    
