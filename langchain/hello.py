<<<<<<< HEAD
from dotenv import load_dotenv
import os

if __name__ == "__main__":
    load_dotenv()
    print("hello langchain")
    print(os.environ['OPENAI_API_KEY'])
=======
from langchain_core.prompt import PromptTemplate
from langchain_openai import ChatOpenAI

if __name__ == "__main__":
    print("Hello, World!")
>>>>>>> 98afdbecff07d124d0063bc06465ab08d9872b57
