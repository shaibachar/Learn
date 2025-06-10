"""example of sliding window algorithm for fixed window size"""
def sliding_window_fixed_size(arr, window_size):
    """Calculates the sum of elements in a sliding window of fixed size.
    Args:
        arr (list): The input array.
        window_size (int): The size of the sliding window.
    """
    if window_size > len(arr):
        return []

    result = []
    current_sum = sum(arr[:window_size])
    result.append(current_sum)

    for i in range(window_size, len(arr)):
        current_sum += arr[i] - arr[i - window_size]
        result.append(current_sum)

    return result

def sliding_window_variable_size(arr, window_size):
    """Calculates the maximum sum of elements in a sliding window of variable size.
    Args:
        arr (list): The input array.
        window_size (int): The size of the sliding window.
    Returns:
        int: The maximum sum of elements in the sliding window.
    Example:
        >>> sliding_window_variable_size([1, 2, 3, 4, 5], 3)
        12
    """
    n = len(arr)
    if window_size > n:
        return 0

    current_sum = 0

    #initial sum of the first window_size
    for i in range(window_size):
        current_sum += arr[i]

    max_sum = current_sum
    start = 0
    end = window_size - 1
    while end < n - 1:
        end += 1
        current_sum += arr[end] - arr[start]
        start += 1

        if current_sum > max_sum:
            max_sum = max(current_sum, max_sum)
    return max_sum