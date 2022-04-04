
import subprocess
import sys

if __name__ == '__main__':
    print(len(sys.argv))
    result = subprocess.run(["/usr/bin/time","-v","ls","-l"],stdout=subprocess.PIPE)
    result.stdout

