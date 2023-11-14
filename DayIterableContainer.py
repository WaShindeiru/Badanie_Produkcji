from IDayIterable import IDayIterable

class DayIterableContainer(IDayIterable):
    def __init__(self):
        super().__init__()
        self.dayIterableList = list()
        self.currentDay = -1
        
    def incrementDay(self):
        for i in self.dayIterableList:
            i.incrementDay()
            self.currentDay += 1

    def addDayIterable(self, dayIterable: IDayIterable):
        self.dayIterableList.append(dayIterable)