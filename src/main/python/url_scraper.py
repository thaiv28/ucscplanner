import requests
from bs4 import BeautifulSoup
import numpy as np
import pandas as pd

def find_subject_pages():
    URL = "https://catalog.ucsc.edu/en/current/general-catalog/courses/"
    
    r = requests.get(URL)
    soup = BeautifulSoup(r.content, 'html.parser')
    
    main = soup.find("ul", class_="sc-child-item-links")
    
    html_links = main.find_all('a', href=True)

    links = []
    for link in html_links:
        links.append(str("https://catalog.ucsc.edu" + link['href']).strip().lower() + "/")
        
    return links

def find_course_pages(subject_page):
    
    r = requests.get(subject_page)
    soup = BeautifulSoup(r.content, 'html.parser')
    
    courselist = soup.find('div', class_="courselist")
    html_names = courselist.find_all('h2', class_='course-name')
    html_links = []
    for div in html_names:
        html_links.append(div.find('a', href=True))
    
    links = []
    for link in html_links:
        links.append(str("https://catalog.ucsc.edu" + link['href']).strip().lower() + "/")
        
    return links
        
    
    