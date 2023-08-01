import sys
import signal
import json
from pynput import mouse
from pynput import keyboard

key_dict = {}

def log_keystroke(key):
    try:
        key_dict[str(key)] += 1
    except KeyError:
        key_dict[str(key)] = 1

def print_statistics():
    for key in key_dict:
        print("{}: {}".format(key, key_dict[key]))

def signal_handler(sig, frame):
    print("Here are your Keystroke Statistics!")
    print_statistics()

    json_object = json.dumps(key_dict)

    with open("data.json", "w") as outfile:
        outfile.write(json_object)
    
    sys.exit(0)

def on_click(x, y, button, pressed):
    if pressed:
        log_keystroke(button)

def on_press(key):
    try:
        log_keystroke(key.char)
    except AttributeError:
        log_keystroke(key)

if __name__ == "__main__":
    signal.signal(signal.SIGINT, signal_handler)

    with open("data.json", "r") as infile:
        key_dict = json.load(infile)
    

    mouse_listener = mouse.Listener(on_click=on_click)
    keyboard_listener = keyboard.Listener(on_press=on_press)

    mouse_listener.start()
    keyboard_listener.start()

    while True:
        pass