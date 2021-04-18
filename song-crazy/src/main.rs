fn main() {
    let animals = vec!["spider", "bird", "cat", "dog", "fennekin"];
    let second_line = vec![
        "That wriggled and iggled and jiggled inside her.",
        "How absurd to swallow a bird.",
        "Imagine that to swallow a cat.",
        "What a hog to swallow a dog.",
        "I think she's burnin'.",
    ];
    start_line("fly", ".");
    end();
    for i in 0..5 {
        start_line(animals[i], ",");
        println!("{}", second_line[i]);
        for j in 0..i {
            let k = i - j;
            swallow_to_catch(animals[k], animals[k - 1]);
        }
        swallow_to_catch("spider", "fly");
        end();
    }
    println!("There was an old woman who swallowed a horse,");
    println!("She died of course.");
}

fn start_line(sth: &str, punctuation: &str) {
    println!(
        "There was an old woman who swallowed a {}{}",
        sth, punctuation
    );
}

fn swallow_to_catch(first: &str, second: &str) {
    println!("She swallowed the {} to catch the {},", first, second);
}

fn end() {
    println!("I don't know why she swallowed that fly,");
    println!("Perhaps she'll die.");
    println!();
}
