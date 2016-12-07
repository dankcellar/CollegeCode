# Timestamp Recorder - By: Eric Downey

import pythoncom, pyHook
import time, datetime
import os

def OnKeyboardEvent(event):

    # Timestamp with F8          
    if event.KeyID == 119:
        ft = time.time()
        seconds = ft - st
        m, s = divmod(seconds, 60)
        h, m = divmod(m, 60)
        timestamp = str('%d:%02d:%02d' % (h, m, s))
        print timestamp
        f.write(timestamp + "\n")

    # Close program with F9
    if event.KeyID == 120: 
        f.close()
        quit()
        
# return True to pass the event to other handlers
    return True

dt = datetime.datetime.now().strftime('%m-%d-%Y Time(%H%M)')
os.chdir('H:\Recordings\Timestamps')                                # CHANGE DIRECTORY HERE
filename = str(dt + ".txt")
f = open(filename, 'a')
f.write(dt + "\n")
print dt

# Start timer
st = time.time()
# create a hook manager
hm = pyHook.HookManager()
# watch for all mouse events
hm.KeyDown = OnKeyboardEvent
# set the hook
hm.HookKeyboard()
# wait forever
pythoncom.PumpMessages()
