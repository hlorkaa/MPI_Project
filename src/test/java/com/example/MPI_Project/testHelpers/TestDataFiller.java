package com.example.MPI_Project.testHelpers;

import com.example.MPI_Project.domain.OrderCard;
import com.example.MPI_Project.domain.Task;
import com.example.MPI_Project.repos.OrderRepo;
import com.example.MPI_Project.service.OrderService;
import com.example.MPI_Project.service.TaskService;

public class TestDataFiller {

    public void  testTaskFill(){
        TaskService taskService = new TaskService();
        taskService.saveTask(new Task("Тестовая задача", "2021-11-29", "Утверждено", "Тестовое описание задачи.", "admin"));
        taskService.saveTask(new Task("Тестовая задача проверки работы камер наблюдения", "2021-11-30", "В процессе", "Проверить работу камер наблюдения за клетками триффидов.", "admin"));
        taskService.saveTask(new Task("Задача составления отчета о запасах масла триффидов за ноябрь", "2021-11-30", "Приостановлено", "Составления отчет о выработке масла триффидов за ноябрь и текущих объемах хранимого масла.", "admin"));
        taskService.saveTask(new Task("Задача расчета стоимости производства масла", "2021-11-27", "Готово", "Рассчитать стоимость производства масла триффидов.", "workman"));
        taskService.saveTask(new Task("Задача поиска решений уменьшения расходов на содержание триффидов", "2021-11-26", "Возникли проблемы", "Найти способы уменьшения расходов на содержание триффидов.", "workman"));
        taskService.saveTask(new Task("Задача скормить провинившегося сотрудника триффидам", "2021-12-01", "Приостановлено", "Схватить провинившегося сотрудника Иванова И.И., раздеть, отмыть и подготовить для употребления в пищу. Отнести тело сотрудника в клетку номер 4 и скормить триффиду. Составить отчет о проделанной работе.", "workman"));
    }

    public void  testOrderFill(){
        OrderService orderService = new OrderService();
        orderService.saveOrder(new OrderCard("Тестовый заказ", "Иванов И.И.", "2021-11-08", "2021-11-18", "Обычное", 10, "Примечания тестового заказа."));
        orderService.saveOrder(new OrderCard("Тестовая партия", "Горшкова С.Е.", "2021-11-07", "2021-11-23", "Техническое", 120, "Тестовая партия масла."));
        orderService.saveOrder(new OrderCard("Срочный заказ!!!", "Блинов Р.Я.", "2021-11-09", "2021-11-19", "Высококачественное", 100, "Очень срочно доставить масло!!!"));
        orderService.saveOrder(new OrderCard("Заказ высококачественного масла", "Петров П.П.", "2021-11-04", "2021-12-11", "Высококачественное", 35, "Нужно произвести требуемое количество высококачественного масла триффидов. Необходимо уложиться в указанный срок. Триффидов кормить только человеческим мясом."));
        orderService.saveOrder(new OrderCard("Заказ масла", "Кузнецов П.А.", "2021-11-06", "2021-11-24", "Техническое", 24, "Заказ масла."));
        orderService.saveOrder(new OrderCard("Масло триффидов", "Павлова Е.Ж.", "2021-11-05", "2021-11-25", "Обычное", 120, "Масло триффидов."));
    }

    public void  deleteTestOrder(String testname){
        OrderService orderService = new OrderService();
        orderService.deleteOrder(testname);
    }
    public void  deleteTestTask(String testname){
        TaskService taskService = new TaskService();
        taskService.deleteTask(testname);
    }

}
