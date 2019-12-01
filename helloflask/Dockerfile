FROM python:3-alpine
MAINTAINER Shai Bachar
COPY . /app
WORKDIR /app
ENV HTTPS_PROXY=genproxy.amdocs.com:8080
RUN pip install -r requirements.txt
ENV FLASK_APP=app
CMD [ "python", "-m", "flask", "run", "--host=0.0.0.0" ]
