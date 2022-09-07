enum SortOrder {ASC, DESC;}

@SuppressWarnings("rawtypes")
class AddressBook implements Iterable {
    private NameAddressPair[] addressBook;
    private int counter = 0;

    public AddressBook(int capacity) {
        this.addressBook = new NameAddressPair[capacity];
    }

    public boolean create(String firstName, String lastName, String address) {
        NameAddressPair.Person personToSave = new NameAddressPair.Person(firstName, lastName);

        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(personToSave)) {
                return false;
            }
        }

        if (addressBook.length == counter) {
            NameAddressPair[] newAddressBook = new NameAddressPair[addressBook.length * 2];
            System.arraycopy(addressBook, 0, newAddressBook, 0, addressBook.length);
            addressBook = newAddressBook;
        }

        addressBook[counter++] = new NameAddressPair(personToSave, address);

        return true;
    }

    public String read(String firstName, String lastName) {

        NameAddressPair.Person personToRead = new NameAddressPair.Person(firstName, lastName);

        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(personToRead)) {
                return addressBook[i].address;
            }
        }

        return null;
    }

    public boolean update(String firstName, String lastName, String address) {
        NameAddressPair.Person personToUpdate = new NameAddressPair.Person(firstName, lastName);

        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(personToUpdate)) {
                addressBook[i].address = address;
                return true;
            }
        }
        return false;
    }

    public boolean delete(String firstName, String lastName) {
        NameAddressPair.Person personToDelete = new NameAddressPair.Person(firstName, lastName);
        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(personToDelete)) {
                System.arraycopy(addressBook, i + 1, addressBook, i, addressBook.length - 1 - i);
                counter--;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return counter;
    }

    public void sortedBy(SortOrder order) {
        Arrays.sort(addressBook, new Comparator<NameAddressPair>() {
            @Override
            public int compare(NameAddressPair o1, NameAddressPair o2) {
                int result = o1.person.firstName.compareTo(o2.person.firstName);
                if (result == 0) {
                    result = o1.person.lastName.compareTo(o2.person.lastName);
                }
                return order == SortOrder.ASC ? result : -result;
            }
        });

    }

    @Override
    public Iterator iterator() {
        return new AddressBookIterator();
    }

    private static class NameAddressPair {

        private final Person person;
        private String address;

        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        private static class Person {
            private String firstName;
            private String lastName;

            private Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Person)) return false;

                Person person = (Person) o;

                if (!firstName.equals(person.firstName)) return false;
                return lastName.equals(person.lastName);
            }

            @Override
            public int hashCode() {
                int result = firstName.hashCode();
                result = 31 * result + lastName.hashCode();
                return result;
            }
        }
    }

    @SuppressWarnings("rawtypes")
    private class AddressBookIterator implements Iterator {

        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter < size();
        }

        @Override
        public Object next() {
            return "First name: " + addressBook[counter].person.firstName + ", Last name: " +
                    addressBook[counter].person.lastName + ", Address: " + addressBook[counter++].address;
        }
    }
}
