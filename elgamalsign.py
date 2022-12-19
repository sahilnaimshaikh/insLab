import sympy


def get_private_key(a, b):
    return sympy.randprime(a, b)


def main():
    message = 100

    # Choosing a large prime p
    p = sympy.randprime(pow(5, 5), pow(5, 10))

    # Setting alpha as 2
    alpha = 2

    # Choosing a random key 'a'
    a = get_private_key(1, p - 1)
    # Calculating beta
    beta = pow(alpha, a, p)

    # Choosing a random value 'k'
    k = get_private_key(1, p - 1)

    # Signature E(x,k) = ((alpha ^ k mod p) , (message - (a * gamma) * k^-1 mod (p-1))
    gamma = pow(alpha, k, p)
    delta = ((message - (a * gamma)) * pow(k, -1, (p - 1))) % (p - 1)

    # Verification
    # alphark is alpha^k
    alphark = pow(alpha, message, p)
    # brggrd is ((beta^gamma)*(gamma^delta))
    brggrd = (pow(beta, gamma, p) * pow(gamma, delta, p)) % p

    print("----------------------------\n ElGamal Signature Scheme \n----------------------------")
    print("Original message(x) : ", message)
    print("large prime(p) : ", p)
    print("secret key(a) : ", a)
    print("alpha : ", alpha)
    print("random prime(k) : ", k)
    print("beta : ", beta)
    print(f"Signature(gamma, delta) : gamma = {gamma}, delta = {delta}")
    print(f"Verification (alpha^k) == ((beta^gamma)*(gamma^delta))? :")
    print(f"(alpha^k) = {alphark} \n((beta^gamma)*(gamma^delta)) = {brggrd}")
    if alphark == brggrd:
        print("Verification successful.")
    else:
        print("Verification unsuccessful.")

if __name__ == '__main__':
    main()
