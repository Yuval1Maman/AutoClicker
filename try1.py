import pyautogui

color1=(244,180,214)
color2 = (247,206,108)
import time

t_end = time.time() + 60

def search():
    print('print')
    for i in range(6):
     s= pyautogui.screenshot(region=(0,0, 100  , 100))
    for x in range(s.width):
        for y in range(s.height):
            if s.getpixel((x, y)) == color1 or s.getpixel((x, y)) == color2:
                pyautogui.click(x,y)
                return
            
if __name__ == "__main__":
    while time.time() < t_end:
        search()