from fastapi import FastAPI

from app import process

app = FastAPI()


@app.get("/")
def read_root():
    return {process.sayHello(): "World!!"}


@app.get("/items/{item_id}")
def read_item(item_id: int, q: str = None):
    return {"item_id": item_id, "q": q}