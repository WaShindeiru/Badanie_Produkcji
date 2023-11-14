from ProductionCost import ProductionCost
from History import History
from Production import Production
from StorageCost import StorageCost
from Donate import Donate
from punishment import Punishment
from TruckQueue import TruckQueue
from DayIterableContainer import DayIterableContainer
from Storage import Storage
from Truck import Truck

class Optimalization:
    def __init__(self, numberOfDays: int, schedule: list, expected: list):
        self.productionCost = ProductionCost()
        self.storageCost = StorageCost()
        self.history = History()
        self.truckQueue = TruckQueue()
        self.storage = Storage()
        self.storageLimit = 700

        self.punishment = Punishment(self.truckQueue)
        self.donate = Donate(self.history)
        
        self.numberOfDays = numberOfDays
        self.schedule = schedule
        self.expected = expected

        self.currentDay = -1
        self.dayContainer = DayIterableContainer()
        self.dayContainer.addDayIterable(self.history)
        self.dayContainer.addDayIterable(self.punishment)

    def incrementDay(self):
        self.currentDay += 1
        self.dayContainer.incrementDay()

    def makeSimulation(self) -> int:
        cost = 0
        self.incrementDay()

        while self.currentDay < self.numberOfDays:
            breakStatus = False
            self.truckQueue.enqueue(Truck(self.expected[self.currentDay]))
            scheduledProduction = self.schedule[self.currentDay]
            production = self.productionCost.getProduction(scheduledProduction)
            self.history.addProduction(production.quantity)

            while not self.truckQueue.isEmpty() and not breakStatus:
                truck = self.truckQueue.peek()
                expectedProduction = truck.getRequirement()

                if expectedProduction <= self.storage.getCurrentStorage() + production.quantity:
                    rest = production.quantity - expectedProduction

                    if rest > 0:
                        currentStorage = self.storage.getCurrentStorage()
                        if currentStorage + rest > self.storageLimit:
                            self.storage.addStorage(self.storageLimit - currentStorage)

                        else:
                            self.storage.addStorage(rest)

                    else:
                        self.storage.takeFromStorage(abs(rest))

                    self.history.addTransaction(expectedProduction, self.storage.getCurrentStorage())
                    self.truckQueue.remove()

                else:
                    currentStorage = self.storage.getCurrentStorage()
                    if production.quantity + currentStorage > self.storageLimit:
                        self.storage.addStorage(self.storageLimit - currentStorage)

                    else:
                        self.storage.addStorage(production.quantity)
                    self.history.addTransaction(0, self.storage.getCurrentStorage())
                    breakStatus = True


            for truck in self.truckQueue:
                truck.incrementDelay()

            cost += production.cost
            cost += self.storageCost.getCost(self.storage.getCurrentStorage())
            cost += self.punishment.getPunishment()

            self.incrementDay()

        cost -= self.donate.getDonate()

        return cost