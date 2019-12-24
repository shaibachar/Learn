import docx2txt

# extract text
text = docx2txt.process("./hello.docx")

def word_count(string):
  my_string = string.lower().split()
  my_dict = {}
  for item in my_string:
    my_dict[item] = item.count(item)
  print(my_dict)

word_count(text)