import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestClassifier
from sklearn.svm import SVC
from sklearn import svm
from sklearn.neural_network import MLPClassifier
from sklearn.metrics import confusion_matrix, classification_report
from sklearn.preprocessing import StandardScaler, LabelEncoder
from sklearn.model_selection import train_test_split

text = ("Welcome to Splitting Text into Individual Words with Regular Expressions."
        "In this video, we're going to take a look at splitting lines of text into"
        "word tokens with the split function, and how we can create a better tokenizer with regular expressions."
        "So, let me explain what some of these things mean")


print(text.split())