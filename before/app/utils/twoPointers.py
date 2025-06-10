## Two Pointers - this function will print a given array from start and from the end
def twoPointers(arr):
    start = 0
    end = len(arr) - 1

    while start <= end:
        if start == end:
            print(arr[start])
        else:
            print(arr[start], arr[end])
        start += 1
        end -= 1