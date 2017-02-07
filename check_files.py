
import os

DEAD_TO_US = ['ClassVisitor',
              'MethodVisitor',
              'FieldVisitor']


def check_files_for_badness(rootdir):
    for subdir, dirs, files in os.walk(rootdir):
        for file in files:
            path = os.path.join(subdir, file)
            f = open(path, 'r')
            s = f.read()
            we_did_bad = map(lambda name: name in s, DEAD_TO_US)
            if True in we_did_bad:
                return True
    return False

if __name__ == '__main__':

    if check_files_for_badness('./src'):
        print('We did bad')
    else:
        print('We did good')
