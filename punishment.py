from IDayIterable import IDayIterable

class Punishment(IDayIterable):
    def __init__(self, truckQueue):
        super().__init__()
        self.currentDay = -1
        self.punishementData = {
            1: 2000,
            2: 5000,
            3: 4000,
            4: 3000,
            5: 5000,
            6: 2000,
            7: 2000,
            8: 2000,
            9: 3000,
            10: 2000
        }

        self.truckQueue = truckQueue

    def incrementDay(self):
        self.currentDay += 1

    def getPunishmentDay(self, day: int) -> int:
        count = 0

        for i in self.truckQueue:
            if i.getDelay() > 0:
                count += 1

        return self.punishementData[day]

    def getPunishment(self) -> int:
        count = 0

        for i in self.truckQueue:
            if i.getDelay() > 0:
                count += 1

        return self.punishementData[self.currentDay + 1]
