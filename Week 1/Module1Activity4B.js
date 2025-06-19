/*
 * Prefix Calculator
 * Module1Activity4B.js
 * 
 * ndavispe
 * 5/30/25
 * SER421 Summer 25
 */

// TEST CASES
/* 
 * const calc1 = new Calculator(10);
 * console.log('initialValue', calc1.currentValue); // 10
 * 
 * console.log('Add 5:', calc1.calc('{"operation": "add", "operand": 5}')); // 15
 * console.log('Subtract 3:', calc1.calc('{"operation": "subtract", "operand": 3}')); // 12
 * console.log('Add 8:', calc1.calc('{"operation": "add", "operand": 8}')); // 20
 *
 * console.log('Current stack:');
 * calc1.printMe(); // [10, 15, 12, 20]
 * 
 * console.log('Peek top:', calc1.peek()); // 20
 * console.log('Peek 2nd from top:', calc1.peek(1)); // 12
 * console.log('Peek 3rd from top:', calc1.peek(2)); // 15
 * 
 * console.log('Undo:', calc1.undo()); // 12
 * calc1.printMe(); // [10, 15, 12]
 */

class Calculator {
    constructor(initialValue = 0) {
        this.stack = [initialValue];
    }

    get currentValue() {
        return this.stack[this.stack.length - 1];
    }

    calc(jsonString) {
        try {
            // 1. parse JSON string
            const expression = JSON.parse(jsonString);
        
            // 2.1. extract operation & operand
            const { operation, operand } = expression;
        
            // 2.2. validate inputs
            if (typeof operation !== 'string') {
                throw new Error('Operation must be String');
            }
            if (typeof operand !== 'number') {
                throw new Error('Operand must be Number');
            }

            let newValue;
        
            // 3. process the operation
            switch (operation.toLowerCase()) {
                case 'add' :
                    newValue = this.currentValue + operand;
                    break;
                case 'subtract' :
                    newValue = this.currentValue - operand;
                    break;
                default :
                    throw new Error(`${operation} Error`);
            }
        
            // 4. push new value to stack + return
            this.stack.push(newValue);
            return newValue;
        
        } catch (error) {
            throw new Error(`${error.message}`);
        }
    }

    undo() {
        // initial check
        if (this.stack.length <= 1) {
            this.stack = [0];
            return 0;
        }

        // remove last operation result
        this.stack.pop();
        return this.currentValue;
    }

    // returns value at the top of the stack without popping the stack
    peek(n = 0) {
        // initial checks
        if (n >= this.stack.length || n < 0 || this.stack.length <= 0) {
            return null;
        }

        if (n === 0) {
            // return top of stack (currentValue)
            return this.currentValue;
        }

        // return n'th element from top
        const index = this.stack.length - 1 - n;
        return this.stack[index];
    }

    // returns the top of the stack and removes that element from the stack, if empty return null
    pop() {
        // initial check
        if (this.stack.length === 0) {
            return null;
        }

        // dont allow last element to be popped
        if (this.stack.length === 1) {
            const value = this.stack[0];
            this.stack = [0];
            return value;
        }

        return this.stack.pop();
    }

    // prints the contents of the stack to the console and does not return a value
    printMe() {
        console.log(this.stack);
    }

    // resets the Calculator to a stack == [0] resets previous operations
    clear() {
        this.stack = [0]
    }
}