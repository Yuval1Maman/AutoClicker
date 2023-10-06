import pyautogui
import time
import threading

color1 = (244, 180, 214)
color2 = (247, 206, 108)

# Define screen regions for each thread
regions = [
    (0, 0, 100, 100),
    (0, 0, 200, 100),
    (0, 0, 300, 100),
    (0, 0, 400, 100),
    (0, 0, 100, 200),
    (0, 0, 200, 200),
    (0, 0, 300, 200),
    (0, 0, 400, 200),
    (0, 0, 100, 300),
    (0, 0, 200, 300),
    (0, 0, 300, 300),
    (0, 0, 400, 300),
    (0, 0, 100, 400),
    (0, 0, 200, 400),
    (0, 0, 300, 400),
    (0, 0, 400, 400),
    (0, 0, 100, 500),
    (0, 0, 200, 500),
    (0, 0, 300, 500),
    (0, 0, 400, 500),
    (0, 0, 100, 600),
    (0, 0, 200, 600),
    (0, 0, 300, 600),
    (0, 0, 400, 600),
    (0, 0, 100, 700),
    (0, 0, 200, 700),
    (0, 0, 300, 700),
    (0, 0, 400, 700),
    (0, 0, 100, 800),
    (0, 0, 200, 800),
    (0, 0, 300, 800),
    (0, 0, 400, 800),
    (0, 0, 100, 900),
    (0, 0, 200, 900),
    (0, 0, 300, 900),
    (0, 0, 400, 900)
]

# Create a lock to control access to the mouse

def search(region):
    print(f'Thread for region {region} started.')
    t_end = time.time() + 10
    while time.time() < t_end:
        s = pyautogui.screenshot(region=region)
        is_break = False
        for x in range(s.width):
            for y in range(s.height):
                if s.getpixel((x, y)) == color1 or s.getpixel((x, y)) == color2:
                    pyautogui.click(x, y)
                    is_break = True
                    break   
            if is_break:
                break
    print(f'Thread for region {region} finished.')

if __name__ == "__main__":
    threads = []
    
    for region in regions:
        thread = threading.Thread(target=search, args=(region,))
        threads.append(thread)
        thread.start()

    for thread in threads:
        thread.join()

    print("All threads finished.")
