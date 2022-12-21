Scenario: Only non negative funds allowed
Given Maria inputs a non zero amount 
Then it is accepted and an account is created


Scenario: Negative start funds are rejected
Given Maria inputs a negative amount to start her fund
Then Her account is not created


