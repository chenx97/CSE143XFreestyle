use rand::thread_rng;
use rand::RngCore;
use std::cmp::min;
use std::io::{stdin, stdout, Write};

const MAX: i32 = 9999;

// now this feels like Java...
macro_rules! print {
    ($($arg:tt)*) => (
        std::print!($($arg)*);
        stdout().flush().unwrap();
    );
}

fn main() {
    intro();

    // plays, guesses, best
    let mut summary_data = vec![0, 0, MAX];
    loop {
        println!();
        print!("How many digits this time? ");
        let mut input = String::new();
        let mut valid = false;
        let mut size: usize = 0;

        // dirty input validation
        while !valid {
            stdin().read_line(&mut input).expect("I/O Error");
            if let Some(number) = match input.trim().parse::<usize>() {
                Ok(number) => Some(number),
                Err(_) => None,
            } {
                if number > 0 {
                    valid = true;
                    size = number;
                } else {
                    error();
                }
            } else {
                error();
            }
        }
        summary_data[0] += 1;
        let guess_count = one_game(&size);
        summary_data[1] += guess_count;
        summary_data[2] = min(guess_count, summary_data[2]);
        input.clear();

        print!("Do you want to play again? ");
        stdin().read_line(&mut input).expect("I/O Error");
        if !(input.starts_with("y") || input.starts_with("Y")) {
            break;
        }
    }
    println!();
    summary(summary_data);
}

fn one_game(size: &usize) -> i32 {
    // main logic
    let mut rng = thread_rng();
    let mut answer = Vec::<i32>::new();
    let mut guess_count = 0;

    // initialize the answer
    for _ in 0..*size {
        answer.push((rng.next_u32() % 9 + 1) as i32);
    }
    // TODO delete
    println!("Hint: {:?}", answer);
    loop {
        print!("Your guess? ");
        let mut txt = String::new();

        // read guess
        stdin().read_line(&mut txt).expect("I/O Error");
        txt = String::from(txt.trim());

        // a quick (?) validation
        if let Some(_) = match txt.parse::<u32>() {
            Ok(_) => Some(()),
            Err(_) => None,
        } {
            guess_count += 1;
            let mut print_string = String::new();
            let mut guess = i32_to_array(txt.parse::<i32>().unwrap());
            if guess.len() == answer.len() {
                let mut answer = answer.clone();

                // fermi
                let fermi = process_fermi(&mut answer, &mut guess, &mut print_string);
                if fermi == answer.len() {
                    break;
                }
                // pico
                process_pico(&mut answer, &mut guess, &mut print_string);

                // bagels
                if print_string.len() == 0 {
                    println!("bagels");
                } else {
                    println!("{}", print_string.trim());
                }
            } else {
                println!("Wrong number of digits!");
            }
        } else {
            error();
        }
    }
    println!(
        "You got it right in {} {}",
        guess_count,
        if guess_count > 1 { "guesses" } else { "guess" }
    );
    guess_count
}

fn intro() {
    println!("Welcome to CSE 143x Bagels!");
    println!("I'll think up a number for you to guess.");
    println!("Each digit will be between 1 and 9.");
    println!("Try to guess my number, and I'll say \"fermi\"");
    println!("for each digit you get right and in the right");
    println!("place, \"pico\" for each digit you get right");
    println!("that is in the wrong place, and \"bagels\"");
    println!("if you don't get any digits right at all.");
}

fn error() {
    println!("Number format Error!");
    println!("Make sure you have a valid natural number.");
}

fn process_fermi(answer: &mut Vec<i32>, guess: &mut Vec<i32>, output: &mut String) -> usize {
    let mut fermi: usize = 0;
    for i in 0..guess.len() {
        if answer[i] == guess[i] {
            answer[i] = -1;
            guess[i] = -1;
            output.push_str("fermi ");
            fermi += 1;
        }
    }
    fermi
}

fn process_pico(answer: &mut Vec<i32>, guess: &mut Vec<i32>, output: &mut String) {
    for i in 0..guess.len() {
        let current = guess[i];
        if current != -1 {
            for j in 0..answer.len() {
                if answer[j] == current {
                    answer[j] = -1;
                    guess[i] = -1;
                    output.push_str("pico ");
                }
            }
        }
    }
}

fn i32_to_array(num: i32) -> Vec<i32> {
    if num == 0 {
        vec![num]
    } else {
        fn helper(input: i32, output: &mut Vec<i32>) {
            if input != 0 {
                helper(input / 10, output);
                output.push(input % 10);
            }
        }
        let mut result = Vec::<i32>::new();
        helper(num, &mut result);
        result
    }
}

fn summary(data: Vec<i32>) {
    println!("Overall results:");
    println!("    total games   = {}", data[0]);
    println!("    total guesses = {}", data[1]);
    println!("    guesses/game  = {:.1}", data[1] as f64 / data[0] as f64);
    println!("    best game     = {}", data[2]);
}
