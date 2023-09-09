import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.Select
import java.time.Duration


fun main() {
    // setup
    val driver = ChromeDriver()
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5))

    start(driver)
    goToCityPage(driver)
    goToAppointmentPage(driver)
    goToConditionsPage(driver)
    goToInfoPage(driver, "ENTER_YOUR_PASSPORT_NUMBER", "ENTER_YOUR_NAME")
    requireAppointment(driver)


    Thread.sleep(3000)
    driver.quit()
}

private fun goToInfoPage(driver: ChromeDriver, passportNumber: String, name: String) {
    clickButton(driver, "rdbTipoDocPas")
    fillField(driver, "txtIdCitado", passportNumber)
    fillField(driver, "txtDesCitado", name)
    driver.executeScript("window.scrollTo(0, document.body.scrollHeight);")
    clickButton(driver, "btnEnviar")
}

private fun requireAppointment(driver: ChromeDriver) {
    clickButton(driver, "btnEnviar")
}

private fun fillField(driver: ChromeDriver, fieldId: String, text: String) {
    driver.findElement(By.id(fieldId)).sendKeys(text)
}

private fun goToConditionsPage(driver: ChromeDriver) {
    driver.executeScript("window.scrollTo(0, document.body.scrollHeight);")
    clickButton(driver, "btnEntrar")
}

private fun goToAppointmentPage(driver: ChromeDriver) {
    selectOption(driver, "tramiteGrupo[0]", "POLICIA-CERTIFICADOS Y ASIGNACION NIE")
    clickButton(driver, "btnAceptar")
}

private fun goToCityPage(driver: ChromeDriver) {
    selectOption(driver, "form", "Valencia")
    clickButton(driver, "btnAceptar")
}

private fun clickButton(driver: ChromeDriver, buttonId: String) {
    driver.findElement(By.id(buttonId)).click()
}

private fun selectOption(driver: ChromeDriver, menuId: String, option: String) {
    val select = Select(driver.findElement(By.id(menuId)))
    select.selectByVisibleText(option)
}

private fun start(driver: ChromeDriver) {
    driver.get("https://icp.administracionelectronica.gob.es/icpplustieb/index.html")
}