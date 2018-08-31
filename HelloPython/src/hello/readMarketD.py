import pandas as pd
import html5lib

from os import listdir
from os.path import isfile, join

def allFiles(mypath):
    onlyfiles = [f for f in listdir(mypath) if isfile(join(mypath, f))]
    return onlyfiles

print(allFiles("../resources/"))

data = pd.read_html('http://www.tapuz.co.il/forums/archive/200/%D7%9E%D7%A9%D7%A4%D7%97%D7%94/%D7%92%D7%A8%D7%95%D7%A9%D7%99%D7%9D_%D7%92%D7%A8%D7%95%D7%A9%D7%95%D7%AA')
print(data)