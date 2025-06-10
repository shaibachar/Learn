import sys
import os

from app.utils import *
# Ensure the app directory is in the Python path
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from pydantic import BaseModel
from typing import List
from fastapi import FastAPI


app = FastAPI()

@app.get("/")
def read_root():
    return {"message": "Hello, FastAPI!"}

@app.get("/two-pointers")
def run_two_pointers(arr: list):
    twoPointers.twoPointers(arr)
    return {"message": "Two pointers function executed."}

@app.get("/palindrome")
def check_palindrome(s: str, method: str = "default"):
    if method == "default":
        result = palindrome.isPalindrome(s)
    elif method == "pointers":
        result = palindrome.isPalindromeWithPointers(s)
    else:
        return {"error": "Invalid method specified. Use 'default' or 'pointers'."}
    
    return {"is_palindrome": result}


class SortedSquaresRequest(BaseModel):
    nums: List[int]
    method: str = "default"

@app.post("/sorted-squares")
def get_sorted_squares(request: SortedSquaresRequest):
    if request.method == "default":
        result = sortedSquares(request.nums)
    elif request.method == "two-pointers":
        print("Using two pointers method for sorted squares")
        result = sortedSquaresTwoPointers(request.nums)
    else:
        return {"error": "Invalid method specified. Use 'default' or 'two-pointers'."}
    
    return {"sorted_squares": result}  


class SortedSquaresRequest(BaseModel):
    nums: List[int]
    method: str = "default"

class SlidingWindowFixedRequest(BaseModel):
    arr: List[int]
    window_size: int

@app.post("/sliding-window-fixed")
def sliding_window_fixed(request: SlidingWindowFixedRequest):
    result = sliding_window_fixed_size(request.arr, request.window_size)
    return {"sliding_window_fixed": result}


@app.post("/sliding-window-variable")
def sliding_window_variable(request: SlidingWindowFixedRequest):
    """Calculates the indices of subarrays that sum to a target value using a variable size sliding window.
    Args:
        arr (list): The input array.
        target_sum (int): The target sum for the subarrays.
    Returns:
        dict: A dictionary containing the indices of subarrays that sum to the target value.
    Example:
        sliding_window_variable([1, 2, 3, 4, 5], 5)
        Returns: {"sliding_window_variable": [(0, 2), (1, 3)]}
    """
    result = sliding_window_variable_size(request.arr, request.window_size)
    return {"sliding_window_variable": result}