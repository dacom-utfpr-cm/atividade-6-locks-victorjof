from datetime import datetime
import re


def make_header():
    headers =  []


    file = "README.md"
    f = open(file).read()

    tasks =[task.strip() +'\n' for task in f.split('\n\n')[1:]]


    for task in tasks:
        headers.append(f"/* \nAuthor: Victor Figueira\nDate:  {datetime.today().strftime('%d/%m/%Y')}\nTask: {task}*/")

    list(map(print,headers))
    
    for n in range(len(headers)):
        with open(f'Exercicio_{n+1}/src/Exercicio_{n+1}.java','r+') as dest:
            past_cont = dest.read()
            dest.seek(0,0)
            dest.write(headers[n].rstrip('\r\n') + '\n' + past_cont)

make_header()
