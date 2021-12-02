# Credit Card System
CS4125 Project

# Running and Compiling
This code is built for the [Java 11 (Corretto) JDK](https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html) and intended to be run in AWS Lambda.

The .jar file for upload to AWS can be created by using "maven clean package", which will output the compiled jar file in the /target directory.

# Testing in Postman
The json file for a postman collection, "CardSystem Demo.postman_collection.json", is included for use.

The requests can be executed in the following order to showcase functionality:

#### CreateUserAccount:
Modify the body of the request to use your email and name, then send it. This should create a user account for you.

#### AccountLogin
Now that you've made a user account, login to it. Make sure to modify the email used in the body of the request to the one you used to make the account.

Logging in will set the environment variable "authToken" automatically based on the response, which will enable you to make the next request.

#### CreateCreditCardAccount
Send this request to make your credit account. Feel free to modify the variables, but doing so may make it so the account isn't approved.

#### AccountLogin
Now that you've made a credit card account, login again. This will give you a new auth token that now contains your accountId.

You can go a website like jwt.io to decode the auth token and see what your account ID is.
Using this ID, you can set the collection variable "accountId".

Now, your auth token contains the accountId and you've set the accountId variable locally, so you're ready to make account specific requests.

#### CheckBalance
Shows current balances on the account.

#### Transaction - Merchant, Cash Advance, and Balance Payment
Makes a transaction. Please note that balance payment amounts must be negative, but merchant and cash advance transactions are positive amounts.

You can make several transactions and see how this impacts your balance.
Because the transactions won't become posted, these will only impact your availableCredit amount.

#### RedeemRewards
Because you don't have a statement created, the rewards balance won't be more than 0, so any redemptions above 0 points won't be approved, and redeemed will be false.
