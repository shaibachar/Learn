FROM python:3.7

WORKDIR /usr/src/app

COPY requirements.txt ./

EXPOSE 80

COPY ./app /app

CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "80"]
