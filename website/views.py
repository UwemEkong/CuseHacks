from flask import Blueprint, render_template

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

@views.route('/results')
def results():
    return render_template("results.html")