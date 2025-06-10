""" this file will contain the sortedSquares function """
def sortedSquares(nums):
    """
    Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
    
    :param nums: List[int] - A list of integers sorted in non-decreasing order.
    :return: List[int] - A list of the squares of each number, sorted in non-decreasing order.
    """
    print("nums:", nums)
    return sorted(x * x for x in nums)

def sortedSquaresTwoPointers(nums):
    """
    Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order using two pointers.
    
    :param nums: List[int] - A list of integers sorted in non-decreasing order.
    :return: List[int] - A list of the squares of each number, sorted in non-decreasing order.
    """
    n = len(nums)
    result = [0] * n
    left = 0
    right = n - 1
    for i in range(n - 1, -1, -1):
        if abs(nums[left]) > abs(nums[right]):
            result[i] = nums[left] ** 2
            left += 1
        else:
            result[i] = nums[right] ** 2
            right -= 1
            
    print("result:", result)
    return result