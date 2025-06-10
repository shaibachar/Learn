def isPalindrome(s: str) -> bool:
    """
    Check if a string is a palindrome.
    
    A palindrome reads the same forwards and backwards, ignoring case and non-alphanumeric characters.
    
    :param s: The string to check.
    :return: True if the string is a palindrome, False otherwise.
    """
    # Normalize the string by removing non-alphanumeric characters and converting to lowercase
    normalized = ''.join(c.lower() for c in s if c.isalnum())
    
    # Check if the normalized string is equal to its reverse
    return normalized == normalized[::-1]

def isPalindromeWithPointers(s: str) -> bool:
    """
    Check if a string is a palindrome using two pointers.
    
    A palindrome reads the same forwards and backwards, ignoring case and non-alphanumeric characters.
    
    :param s: The string to check.
    :return: True if the string is a palindrome, False otherwise.
    """
    start, end = 0, len(s) - 1
    
    while start < end:
        # Move start pointer to the next alphanumeric character
        while start < end and not s[start].isalnum():
            start += 1
        # Move end pointer to the previous alphanumeric character
        while start < end and not s[end].isalnum():
            end -= 1
        
        # Compare characters in a case-insensitive manner
        if s[start].lower() != s[end].lower():
            return False
        
        start += 1
        end -= 1
    
    return True