import subprocess
from subprocess import PIPE
l = ['lol', 'fos']

p = subprocess.Popen(['java', 'App.java'], stdin=PIPE, stdout=PIPE)
for cmd in l:   
    p.stdin.write(bytes(cmd + '\n', 'utf-8'))
