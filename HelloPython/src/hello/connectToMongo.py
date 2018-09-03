import sys
import datetime

from pymongo import MongoClient
from pprint import pprint

client = MongoClient('mongodb://localhost:27017/test')

# use tasks database
db = client.tasks

# clean all table
db.tasks.drop()

task1 = {
    'name': 'sports',
    'duration': '10',
    'last_modified': datetime.datetime.now()
}
task2 = {
    'name': 'eat',
    'duration': '100',
    'last_modified': datetime.datetime.now()
}

tasks = [task1, task2]
# insert all tasks
result = db.tasks.insert_many(tasks)

# print count on tasks
count = db.tasks.find().count()
print("tasks count:", count)

selected = db.tasks.find_one({'name': 'sports'})
print("task1 duration from DB:", selected['duration'])
