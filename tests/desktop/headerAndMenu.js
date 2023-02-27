const assert = require('chai').assert;


describe('header and menu', async function () {
    beforeEach(async (opts) => {
        opts.host = 'http://localhost:8080/'
        opts.auth_params = {
            director: {
                login: 'khilike',
                password: 'password'
            },
            worker: {
                login: 'ivanetsas',
                password: 'password'
            },
            screenplay_writer: {
                login: 'nosovas',
                password: 'password'
            },
            requester: {
                login: 'okhlobystini',
                password: 'password'
            }
        }
        opts.director_login = 'khilike'
        opts.director_password = 'password'
    });

    async function auth(host, browser, login, password) {
        await logout(browser, host);
        await browser.url(host);

        await browser.$('#username').addValue(login);
        await browser.$('#password').addValue(password);
        await browser.$('.singInButton').click();
        await browser.pause(60);
    }

    async function logout(browser, host) {
        await browser.url(host + 'logout');
        await browser.pause(120);

    }

    it('check director username and role labels', async ({browser, host, auth_params}) => {
        await auth(host, browser, auth_params.director.login, auth_params.director.password);

        const systemTitle = await browser.$('#systemName').getText();
        assert.equal(systemTitle, 'Информационная система Холоп');
        const loginUserName = await browser.$('#usernamePosition > p').getText();
        assert.equal(loginUserName, 'Egor Khilik')
        await browser.$('#userIconPosition').click()
        const loginUserRole = await browser.$('#user-position-label').getText()
        assert.equal(loginUserRole, 'Режиссер')

        const mainMenuButton = await browser.$("#menu_container > a:nth-child(2)").getText();
        assert.equal(mainMenuButton, "Главная страницa");
        const newTaskButton = await browser.$("#menu_container > a:nth-child(3)").getText();
        assert.equal(newTaskButton, "Новая задача");
        const tasksButton = await browser.$("#menu_container > a:nth-child(4)").getText();
        assert.equal(tasksButton, "Текущие задачи");
        const predictionButton = await browser.$("#menu_container > a:nth-child(5)").getText();
        assert.equal(predictionButton, "Прогнозирование");
        const listRolesButton = await browser.$("#menu_container > a:nth-child(6)").getText();
        assert.equal(listRolesButton, "Список ролей");
        const listScreenplaysButton = await browser.$("#menu_container > a:nth-child(7)").getText();
        assert.equal(listScreenplaysButton, "Список сценариев");

        await logout(browser, host);
    });


    it('check worker username and role labels', async ({browser, host, auth_params}) => {
        await auth(host, browser, auth_params.worker.login, auth_params.worker.password);

        const systemTitle = await browser.$('#systemName').getText();
        assert.equal(systemTitle, 'Информационная система Холоп');
        const loginUserName = await browser.$('#usernamePosition > p').getText();
        assert.equal(loginUserName, 'Alexander Ivanets')
        await browser.$('#userIconPosition').click()
        const loginUserRole = await browser.$('#user-position-label').getText()
        assert.equal(loginUserRole, 'Сотрудник')

        const mainMenuButton = await browser.$("#menu_container > a:nth-child(2)").getText();
        assert.equal(mainMenuButton, "Главная страницa");
        const tasksButton = await browser.$("#menu_container > a:nth-child(3)").getText();
        assert.equal(tasksButton, "Текущие задачи");
        const listRolesButton = await browser.$("#menu_container > a:nth-child(4)").getText();
        assert.equal(listRolesButton, "Список ролей");
        const listScreenplaysButton = await browser.$("#menu_container > a:nth-child(5)").getText();
        assert.equal(listScreenplaysButton, "Список сценариев");

        await logout(browser, host);
    });

    it('check screenplay writer username and role labels', async ({browser, host, auth_params}) => {
        await auth(host, browser, auth_params.screenplay_writer.login, auth_params.screenplay_writer.password);

        const systemTitle = await browser.$('#systemName').getText();
        assert.equal(systemTitle, 'Информационная система Холоп');
        const loginUserName = await browser.$('#usernamePosition > p').getText();
        assert.equal(loginUserName, 'Svetlana Nosova')
        await browser.$('#userIconPosition').click()
        const loginUserRole = await browser.$('#user-position-label').getText()
        assert.equal(loginUserRole, 'Сценарист')

        const mainMenuButton = await browser.$("#menu_container > a:nth-child(2)").getText();
        assert.equal(mainMenuButton, "Главная страницa");
        const listRolesButton = await browser.$("#menu_container > a:nth-child(3)").getText();
        assert.equal(listRolesButton, "Список ролей");
        const newRoleButton = await browser.$("#menu_container > a:nth-child(4)").getText();
        assert.equal(newRoleButton, "Новая роль");
        const listScreenplaysButton = await browser.$("#menu_container > a:nth-child(5)").getText();
        assert.equal(listScreenplaysButton, "Список сценариев");
        const newScreenplayButton = await browser.$("#menu_container > a:nth-child(6)").getText();
        assert.equal(newScreenplayButton, "Новый сценарий");

        await logout(browser, host);
    });

    it('check requester username and role labels', async ({browser, host, auth_params}) => {
        await auth(host, browser, auth_params.requester.login, auth_params.requester.password);

        const systemTitle = await browser.$('#systemName').getText();
        assert.equal(systemTitle, 'Информационная система Холоп');
        const loginUserName = await browser.$('#usernamePosition > p').getText();
        assert.equal(loginUserName, 'Ivan Okhlobystin')
        await browser.$('#userIconPosition').click()
        const loginUserRole = await browser.$('#user-position-label').getText()
        assert.equal(loginUserRole, 'Заказчик')

        const mainMenuButton = await browser.$("#menu_container > a:nth-child(2)").getText();
        assert.equal(mainMenuButton, "Главная страницa");
        const predictionButton = await browser.$("#menu_container > a:nth-child(3)").getText();
        assert.equal(predictionButton, "Прогнозирование");
        const listRolesButton = await browser.$("#menu_container > a:nth-child(4)").getText();
        assert.equal(listRolesButton, "Список ролей");
        const listScreenplaysButton = await browser.$("#menu_container > a:nth-child(5)").getText();
        assert.equal(listScreenplaysButton, "Список сценариев");

        await logout(browser, host);
    });

});