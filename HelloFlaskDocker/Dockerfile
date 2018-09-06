FROM python:3.6-alpine
ADD . /todo
WORKDIR /todo
RUN pip install -r requirements.txt
CMD ["gunicorn", "-w 4", "main:app"]