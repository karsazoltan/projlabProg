import os
import subprocess
from subprocess import PIPE, STDOUT
import time
import sys

succesful = 0
nr = 44
path = ''
test_folder = 'tesztek'
tests = os.listdir("tesztek/")
full_path = os.path.abspath(os.getcwd())
start = 0
end = 42



# This function checks the out.txt file of a given test case.
def check_save_file(directory, index):
    path = os.path.join(full_path, test_folder, directory, index + '-cmd.txt')
    cmd = open(path, 'r')
    p = subprocess.Popen(['java','-jar', 'projlabProg.jar'], stdin=PIPE, stdout=PIPE, stderr=STDOUT)
    for command in cmd.readlines():
        if 'load' in command:
            command = 'load ' + test_folder + '\\' + directory + '\\' + index + '-in.txt\n'
        elif 'save' in command:
            command = 'save ' + test_folder + '\\' + directory + '\\' + index + '-out.txt\n'
            p.communicate(bytes(command, 'utf-8'))
            break
        p.stdin.write(bytes(command, 'utf-8'))
    cmd.close()

    okay = True
    ep = os.path.join(full_path, test_folder, directory, index + '-expected.txt')
    gp = os.path.join(full_path, test_folder, directory, index + '-out.txt')

    with open(ep, 'r') as expected, open(gp, 'r') as generated:    
        expected_lines = [line for line in expected]
        generated_lines = [line for line in generated]

        if len(expected_lines) != len(generated_lines):
            okay = False
        if okay:
            for e, g in zip(expected_lines, generated_lines):
                e = e.strip('\n').replace(' ', '')
                g = g.strip('\n').replace(' ', '')
                if sorted(e) != sorted(g):
                    print(e)
                    print(g)
                    okay = False
                    break
    return okay




# This function checks the standard output of a given test case.
def check_stdout(directory, index):
    path = os.path.join(full_path, test_folder, directory, index + '-cmd.txt')
    ep = os.path.join(full_path, test_folder, directory, index + '-expected.txt')
    expected = open(ep, 'r')
    expected_lines = [line for line in expected]
    
    cmd = open(path, 'r')
    p = subprocess.Popen(['java','-jar', 'projlabProg.jar'], stdin=PIPE, stdout=PIPE, stderr=STDOUT)
    for command in cmd.readlines():
        if 'load' in command:
            command = 'load ' + test_folder + '\\' + directory + '\\' + index + '-in.txt\n'
        p.stdin.write(bytes(command, 'utf-8'))
    stdout = p.communicate()[0].decode("utf-8").split('\n')
    cmd.close()
    expected.close()

    problematic_chars = [' ', '\n', '\r', ',', '>', '.', '!', "'", ':']
    for pc in problematic_chars:
        stdout = [x.replace(pc, '') for x in stdout]
        expected_lines = [x.replace(pc, '') for x in expected_lines]
    
    inside = 0
    for el in expected_lines:
        if el in stdout:
            inside += 1
    
    if inside == len(expected_lines):
        return True 
    else:
        return False



# Main function that iterates on the given test cases.
def main():
    global start, end, tests, succesful
    if len(sys.argv) == 3:
        start = int(sys.argv[1])
        end = int(sys.argv[2])
    
    directories = []
    tests = sorted(tests, key = lambda x:int(x[:2]))
    for directory in tests:
        directories.append(directory)

    for directory in directories[start:end + 1]:
        index = directory[:2].strip(' ')
        stdout_ouptut = directory[-1] == '1'

        if not stdout_ouptut:
            okay = check_save_file(directory, index)
        else:
            okay = check_stdout(directory, index)
        

        if okay:
            succesful += 1
            print('[INFO] Test ' + index + '. succeeded.')
        else:
            print('[WARNING] Test ' + index + '. failed.')

    if succesful == end - start + 1:
        print('[DONE] Every test was succesful.')
    else:
        print('[DONE] Task failed succesfully.')


main()
