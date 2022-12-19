import sympy


def get_prime_three_mod_four():
    pr = sympy.randprime(pow(5, 5), pow(5, 6))
    return pr if pr % 4 == 3 else get_prime_three_mod_four()


def gcdExtended(a, b):
    if a == 0:
        return b, 0, 1

    gcd, x1, y1 = gcdExtended(b % a, a)
    x = y1 - (b // a) * x1
    y = x1
    return gcd, x, y


def main():
    # Choosing large primes p and q, Calculating n
    p = get_prime_three_mod_four()
    q = get_prime_three_mod_four()

    # n is the public key
    n = p * q

    message = sympy.randprime(1, n)

    # Encryption C = x^2 mod n
    C = pow(message, 2, n)

    # Decryption using square roots
    mp = pow(C, int((p + 1) / 4), p)
    mq = pow(C, int((q + 1) / 4), q)

    gcd, a, b = gcdExtended(p, q)

    X = ((a*p*mq) + (b*q*mp)) % n
    Y = ((a*p*mq) - (b*q*mp)) % n

    print("----------------------------\n Rabin Cryptosystem \n----------------------------")
    print("Original message(x) : ", message)
    print("large prime(p) : ", p)
    print("large prime(p) : ", q)
    print("public key(n) : ",n)
    print("Encrypted ciphertext (C) : ", C)
    print("Decrypted message will be one of the following : ")
    print(f"{X}\n{Y}\n{n-X}\n{n-Y}")

if __name__ == '__main__':
    main()
