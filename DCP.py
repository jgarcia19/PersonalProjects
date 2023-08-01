def func(weekday, vacation):
    if not weekday | vacation:
        return True
    return False


if __name__ == "__main__":
    func(True, True)