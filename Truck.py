class Truck:
    def __init__(self, requirement: int):
        self.requirement = requirement
        self.delay = 0
        
    def incrementDelay(self):
        self.delay += 1
        
    def getDelay(self) -> int:
        return self.delay

    def getRequirement(self):
        return self.requirement