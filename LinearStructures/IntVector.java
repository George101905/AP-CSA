    public class IntVector{

        private final static int INITIAL_CAPACITY = 10;
        private int[] arr = new int[INITIAL_CAPACITY];
        private int size = 0;

        public IntVector() { }

        public IntVector(int capacity){
            if(capacity < 0){
                throw new IllegalArgumentException();
            }
            arr = new int[capacity];
        }

        //the size function returns the size
        public int size() {return size;}

        //CRUD operations (this is 99% of business)

        private void testBounds(int index){
            if(index < 0 || index >= size){
                throw new IndexOutOfBoundsException();
            }
        }

        private void tryResize(){
            if(size >= arr.length - 1){
                int[] temp = new int[1 + arr.length * 2];
                for(int i = 0; i < size; i++){
                    temp[i] = arr[i];
                }
                arr = temp;
            }
        }
        // insert the value "n" at the end of the vector
        public void add(int n){
            tryResize();
            arr[size ++] = n;
        }

        private void resize(){
            if(size >= arr.length - 1){ // reached the end of the array
                //make new temp array
                int[] temp = new int[1 + arr.length * 2];

                //copy data to the temp
                for(int i = 0; i < size; i++){
                    temp[i] = arr[i];
                }

                //set old array = new temp
                arr = temp;
            }
        }

        public int get(int index){
            testBounds(index);
            return arr[index];
        }

        public void clear() {
            arr = new int[INITIAL_CAPACITY];
            size = 0;
        }

        /**
         * Sets the elment at 'index' equal to 'value'
         * Shifts other elements to the right
         * Returns 'true' if the value was succefully inserted, 'False' otherwise
         * @param index The position in which the value will be inserted
         * @param value The numerical value to be added
         */
        public boolean add(int index,int value){
            // SOLVE
            testBounds(index);
            int temp = arr[index];
            arr[index] = value;
            add(arr[size - 1]);
            for(int i = size - 2; i > index; i--){
                arr[i+1] = arr[i];
            }
            arr[index + 1] = temp;
            if(arr[index] == value){
                return true;
            }
            return false;
        }

        /**
         * Removes the first instance of 'value' found in the list
         * Shifts remaining values left if neccesary
         * Returns 'true' if the value was removed from the list, 'false' otherwise
         * The list remains unchanged if the value does not exist in the list
         * @param value The value to be removedif it exists
         */
        public boolean remove(int value){
            // SOLVE
            for(int i = 0; i < size; i++){
                if(arr[i] == value){
                   for(int j = i; j < size - 1; j++){
                    arr[j] = arr[j+1];
                   } 
                   size--;
                   tryResize();
                   return true;
                }
            }
            return false;
        }

        @Override
        public String toString(){
            if(size == 0)return "[]";
            String t = "[" + arr[0];
            for(int i = 1; i < size; i++){
                t += ", " + arr[i];
            }
            return t + "]";
        }
    }