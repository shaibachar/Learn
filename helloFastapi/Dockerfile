FROM python:3.7

WORKDIR /usr/src/app

COPY requirements.txt ./
RUN pip install --no-cache-dir --upgrade pip --proxy=http://genproxy.corp.amdocs.com:8080\
  && pip install --no-cache-dir -r requirements.txt --proxy=http://genproxy.corp.amdocs.com:8080

EXPOSE 80

COPY ./app /app

CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "80"]
