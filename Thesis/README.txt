Historical newspaper archives provide a wealth of information. They
are of particular interest to genealogists, historians and scholars
for People Search.

 In this thesis, we design a People Gazetteer from
the noisy OCR text of historical newspapers and identify “influential”
people from it. A People Gazetteer is a dictionary of personal names;
each entry of the gazetteer is  a tuple containing a person name and a
list of articles in which his name occurs.

To build the People Gazetteer, we first spell correct the noisy text
using an edit distance based algorithm. A novel N-gram based
evaluation algorithm is designed for measuring the performance of the
spell corrector. Next, a Named Entity Recognizer is run on the text of
each article to identify person entities and an LDA-based topic
detector to assign categories to articles. To identify influential
people in each category of the People Gazetteer, we define the notion
of an Influential Person Index (IPI) and rank based on it. Our corpus
is a sample of 14020 newspaper articles (roughly two months’ data)
obtained from “The Sun” newspaper in the Chronicling America project.