from flask import Blueprint, render_template, request, redirect, flash
import os
views = Blueprint('views', __name__)

@views.route('/')
def home():
    return render_template("index.html")

@views.route('/howitworks')
def how_it_works():
    return render_template("howitworks.html")

@views.route('/aboutus')
def about_us():
    return render_template("aboutus.html")

@views.route('/results', methods=['GET', 'POST'])
def results():
    if request.method == 'POST':
        link = request.form["nm"]
        created_summary = get_summary(link)
    return render_template("results.html", summary = created_summary)

def get_summary(link):
    import nltk
    from nltk.corpus import stopwords
    from nltk.tokenize import word_tokenize, sent_tokenize
    nltk.download('stopwords')
    nltk.download('punkt')
    import bs4 as bs
    import urllib.request
    import re

    scraped_data = urllib.request.urlopen(link)
    article = scraped_data.read()

    parsed_article = bs.BeautifulSoup(article,'html.parser')

    paragraphs = parsed_article.find_all('p')

    text = ""

    for p in paragraphs:
        text += p.text
   
    stopWords = set(stopwords.words("english"))
    words = word_tokenize(text)

    freqTable = dict()
    for word in words:
        word = word.lower()
        if word in stopWords:
            continue
        if word in freqTable: 
            freqTable[word] += 1
        else: 
            freqTable[word] = 1

    sentences = sent_tokenize(text)
    sentenceValue = dict()
   
    for sentence in sentences:
        for word, freq in freqTable.items():
            if word in sentence.lower():
                if sentence in sentenceValue:
                    sentenceValue[sentence] += freq
                else: 
                    sentenceValue[sentence] = freq 
    
    sumValues = 0
    for sentence in sentenceValue:
        sumValues += sentenceValue[sentence]
    
    average = int(sumValues / len(sentenceValue))

    summary = ''
    for sentence in sentences:
        if (sentence in sentenceValue) and (sentenceValue[sentence] > (1.5 * average)):
            summary += " " + sentence

    word_list= summary.split( )

    for word in word_list:
        if ']' in word or '[' in word:
            word_list.remove(word)

    final_summary = ''

    for word in word_list:
        final_summary+= word + " " 
    return final_summary