(function() { // encapsulate actions within a function
    // 1. update "Help Spread DuckDuckGo" to "Help Spread Love!"
    const button = document.getElementById('spread');
    if (button) {
        // needed
        button.innerHTML = '<img class="header__clickable--icon" src="./activity1_files/heart.svg"> Help Spread Love!';
        console.log('button updated');
    } else {
        console.log('button not found');
    }

    // 2. output <li> tag count
    const liCount = document.querySelectorAll('li').length;
    console.log('number of <li> tags :', liCount);

    // 3. get search bar value
    const searchInput = document.getElementById('search_form_input');
    if (searchInput) {
        console.log('search bar value :', searchInput.value);
    } else {
        console.log('search bar not found');
    }

    // 4. remove duck logo
    const logo = document.querySelector('.header__logo');
    if (logo) {
        logo.remove();
        console.log('logo removed');
    } else {
        console.log('logo not found');
    }
})();