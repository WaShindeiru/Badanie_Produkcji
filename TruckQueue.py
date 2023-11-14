import Truck
import TruckQueue

class TruckQueue:
    def __init__(self):
        self.queue = list()
        self._index = 0
        self.size = 0
        
    def remove(self) -> Truck:
        self.size -= 1
        return self.queue.pop(0)
         
    def enqueue(self, truck: Truck):
        self.size += 1
        self.queue.append(truck)

    def getSize(self):
        return self.size

    def isEmpty(self):
        return self.size == 0

    def peek(self):
        return self.queue[0]

    def __iter__(self) -> TruckQueue:
        self._index = 0
        return self
    
    def __next__(self):
        if self._index < len(self.queue):
            item = self.queue[self._index]
            self._index += 1
            return item
        else:
            raise StopIteration