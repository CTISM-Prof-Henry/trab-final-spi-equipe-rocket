// Função para definir a cor da barra baseado na evasão
function getBarColor(evasao) {
    if (evasao > 0.7) return "bg-danger";
    if (evasao > 0.4) return "bg-warning";
    if (evasao > 0.2) return "bg-info";
    return "bg-success";
}

// Função para renderizar o gráfico de histórico de evasão em barra
function renderizarGraficoBarra(aluno) {
    const canvas = document.getElementById("graficoEvasaoBarra");
    if (!canvas) return;

    const ctx = canvas.getContext("2d");

    // Pega os anos e probabilidades do histórico
    const anos = Object.keys(aluno.historicoEvasao || {});
    const probabilidades = Object.values(aluno.historicoEvasao || {}).map(p => (p * 100).toFixed(1));


    if (anos.length === 0) return; // Sem histórico

    // Determinar cores das barras
    const maxValor = Math.max(...probabilidades.map(Number));
    const backgroundColors = probabilidades.map(p => {
        const valor = parseFloat(p);
        if (valor === maxValor) return "rgba(255, 99, 132, 0.8)"; // Destaque
        else if (valor > 70) return "rgba(255, 159, 64, 0.6)";
        else if (valor > 40) return "rgba(255, 206, 86, 0.6)";
        else return "rgba(75, 192, 192, 0.6)";
    });
    const borderColors = backgroundColors.map(color => color.replace("0.6", "1").replace("0.8", "1"));

    new Chart(ctx, {
        type: "bar",
        data: {
            labels: anos,
            datasets: [{
                label: "Histórico de Evasão (%)",
                data: probabilidades,
                backgroundColor: backgroundColors,
                borderColor: borderColors,
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true,
                    max: 100,
                    ticks: {
                        callback: value => value + "%"
                    }
                }
            }
        }
    });
}

// Preencher os dados do aluno na página
document.addEventListener("DOMContentLoaded", () => {
    if (!aluno) {
        console.error("Dados do aluno não encontrados!");
        return;
    }

    document.getElementById("nome-aluno").textContent = aluno.nome;
    document.getElementById("matricula-aluno").textContent = aluno.matricula;
    document.getElementById("curso-aluno").textContent = aluno.nomeCurso;
    document.getElementById("evasao-aluno").textContent = aluno.evasao.toFixed(1) + "%";


    // Renderiza gráfico de histórico de evasão
    renderizarGraficoBarra(aluno);
});
