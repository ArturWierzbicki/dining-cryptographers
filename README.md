# Dining cryptographers

Project contains implementations of following protocols used to solve the dining cryptographers problem:
Protocol             | Paper                                                                                | Authors                   
-------------------- | ------------------------------------------------------------------------------------ | ---------------------------
DC-Net              | The Dining Cryptographers Problem: Unconditional Sender and Recipient Untraceability | David Chaum               
[In progress] AV-Net | A 2-Round Anonymous Veto Protocol                                                    | Feng Hao, Piotr Zielinski 

# Problem statement

The problem these protocols solve is how to send a bit of information to other participants of the protocol anonymously, or, rephrased, how to find out whether anyone in a group has vetoed without getting to know his identity.


### Security 

In DC-Net there are 3 main security problems:
* Collisions - if two protocol participants vetoed, they veto would cancel out
* Without proofs/commitments to broadcasts, participants can change them to mess with protocol
* Cost of setup - protocol requires that every participant has shared key with every other participant



AV-Nets solves them:
* Chance for collision is negligible, intentional collisions are as hard to do as DDH
* Proofs to secrets are required
* Setup time is linear, it involves a single exponentiation from every participant


AV-Net implementation still does not have built in zero knowledge proofs. They shall follow 

### AV-Net primitives

AV-Net implementation uses 2 cryptographic primitives:
* Zero knowledge proofs for making sure that participants can not change their vote after gaining knowledge of other peoples vote
* DDH problem hardness to make sure that last participant of protocol can not change a non-1 result into 1


https://crypto.stackexchange.com/questions/24063/zero-knowledge-non-interactive-proof-with-random-oracle
https://en.wikipedia.org/wiki/Dining_cryptographers_problem
http://homepages.cs.ncl.ac.uk/feng.hao/files/av_net.pdf
http://www.cs.cornell.edu/People/egs/herbivore/dcnets.html


