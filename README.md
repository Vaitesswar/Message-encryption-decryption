# Message-encryption-decryption

## Requirement ##
You are to write an encoder that takes in a plaintext and encode it to another obfuscated string.

## Logic ##
- Choose any character in the reference table as the offset. The first character of the encoded message will be the offset character. Any character not in the reference table will mapped back to the same character. For example, if the offset character is B, the entire table will shift by 1 element down.
- Thus, given the plaintext HELLO WORLD, it will be encoded as BGDKKN VNQKC:
-- H E L L O W O R L D
-- B G D K K N V N Q K C
- Let’s take F as the offset character for another example. The entire table will shift 5 elements down (Refer to Shift
Table #2). Given the same plaintext, the encoded message will be:
-- H E L L O W O R L D
-- F C / G G J R J M G .
- To decode it, you need to take the first character for offset and match it backwards to get the original plaintext.
