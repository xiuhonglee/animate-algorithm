import matplotlib.pyplot as plt
from Bio.Align import PairwiseAligner

def plot_alignment(align1, align2):
    """Visualize the sequence alignment."""
    fig, ax = plt.subplots(figsize=(len(align1) * 0.5, 3))
    ax.axis('off')
    for i, (a1, a2) in enumerate(zip(align1, align2)):
        if a1 == a2:
            ax.plot([i, i], [1, 2], color='k')
        ax.text(i, 1, a1, ha='center', va='center')
        ax.text(i, 2, a2, ha='center', va='center')
    ax.set_ylim(0.5, 2.5)
    plt.show()

seq1 = "ATCGGATTAG"
seq2 = "GATCTAGTA"

aligner = PairwiseAligner()
alignments = aligner.align(seq1, seq2)

# For the sake of visualization, let's pick the first alignment
alignment = alignments[0]
align1, align2 = alignment[0], alignment[1]

plot_alignment(align1, align2)

