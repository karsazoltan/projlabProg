import os 
import time


path = os.path.abspath(os.getcwd())
java_files = [os.path.join(root, name)
             for root, dirs, files in os.walk(path)
             for name in files
             if name.endswith((".java"))]

txt_files = [os.path.join(root, name)
             for root, dirs, files in os.walk(path)
             for name in files
             if name.endswith((".txt"))]


java_sizes = [os.path.getsize(x) for x in java_files]
txt_sizes = [os.path.getsize(x) for x in txt_files]
java_created = [os.path.getctime(x) for x in java_files]
txt_created = [os.path.getctime(x) for x in txt_files]
java_files = [x.split('\\')[len(x.split('\\'))-1] for x in java_files]
txt_files = [x.split('\\')[len(x.split('\\'))-1] for x in txt_files] 


file = open('latex.txt', 'w', encoding='utf-8')
for file_name, size, created in zip(java_files, java_sizes, java_created):
    file.write("\\fajl\n")
    file.write('{ ' + file_name + '}\n')
    file.write('{ ' + str(size) + ' byte }\n')
    file.write('{ ' + time.ctime(created) + ' }\n')
    file.write('{ ' + file_name.split('.')[0] + ' programosztály }\n')
    file.write('\n')

for file_name, size, created in zip(txt_files, txt_sizes, txt_created):
    file.write("\\fajl\n")
    file.write('{ ' + file_name + ' }\n')
    file.write('{ ' + str(size) + ' byte }\n')
    file.write('{ ' + time.ctime(created) + ' }\n')
    file.write('{ ' + file_name.split('.')[0] + ' tesztesetfájl }\n')
    file.write('\n')
