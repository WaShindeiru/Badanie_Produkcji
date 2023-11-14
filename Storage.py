class Storage:
    def __init__(self):
        self.currentStorage = 0
        self.storageLimit = 700

    def addStorage(self, storage: int):
        if self.currentStorage + storage <= self.storageLimit:
            self.currentStorage += storage

        else:
            raise Exception

    def getCurrentStorage(self) -> int:
        return self.currentStorage

    def takeFromStorage(self, amount) -> int:
        if amount <= self.currentStorage:
            self.currentStorage -= amount
            return amount

        raise Exception